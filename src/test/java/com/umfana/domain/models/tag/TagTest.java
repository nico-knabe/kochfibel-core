package com.umfana.domain.models.tag;

import com.umfana.domain.DomainException;
import com.umfana.domain.ExecutedCommand;
import com.umfana.domain.models.tag.commands.ChangeTagCommand;
import com.umfana.domain.models.tag.commands.CreateTagCommand;
import com.umfana.domain.models.tag.commands.DeleteTagCommand;
import com.umfana.domain.models.tag.events.TagCreatedEvent;
import com.umfana.domain.models.tag.valueobjects.TagColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

class TagTest {

    private static final TagId TAG_ID = new TagId(java.util.UUID.randomUUID());
    private static final Instant NOW = Instant.now();

    @Test
    void createTagTest() {
        Tag tag = createTag("name");
        Assertions.assertNotNull(tag.getId());
        Assertions.assertEquals("name", tag.getName());
        Assertions.assertEquals(TagColor.BLUE, tag.getColor());
        Assertions.assertFalse(tag.isDeleted());
        Assertions.assertEquals(1, tag.getUncommittedEvents().size());
        TagCreatedEvent tagCreatedEvent = (TagCreatedEvent) tag.getUncommittedEvents().getFirst();
        assertTagCreatedEvents(tagCreatedEvent, "name", TagColor.BLUE);
    }

    @Test
    void changeTagTest() {
        Tag tag = createTag("name");
        changeTag(tag);
        Assertions.assertEquals("newName", tag.getName());
        Assertions.assertEquals(TagColor.RED, tag.getColor());
        Assertions.assertFalse(tag.isDeleted());
    }

    @Test
    void deleteTagTest() {
        Tag tag = createTag("name");
        deleteTag(tag);
        Assertions.assertTrue(tag.isDeleted());
    }

    @Test
    void couldNotCreateCreatedTagTest() {
        Tag tag = createTag("name");
        ExecutedCommand<CreateTagCommand> createTagCommand = new ExecutedCommand<>(new CreateTagCommand(TAG_ID, "name", TagColor.BLUE), NOW);
        DomainException exception = Assertions.assertThrows(DomainException.class, () -> tag.create(createTagCommand));
        Assertions.assertEquals(DomainException.Key.CouldNotCreateTag, exception.getDescription());
    }

    @Test
    void couldNotCreateDeletedTagTest() {
        Tag tag = createTag("name");
        deleteTag(tag);
        ExecutedCommand<CreateTagCommand> createTagCommand = new ExecutedCommand<>(new CreateTagCommand(TAG_ID, "name", TagColor.BLUE), NOW);
        DomainException exception = Assertions.assertThrows(DomainException.class, () -> tag.create(createTagCommand));
        Assertions.assertEquals(DomainException.Key.CouldNotCreateTag, exception.getDescription());
    }

    @Test
    void couldNotCreateTagWithEmptyNameTest() {
        DomainException exception = Assertions.assertThrows(DomainException.class, () -> createTag(""));
        Assertions.assertEquals(DomainException.Key.TagNameDoesNotBeBlank, exception.getDescription());
    }

    @Test
    void couldNotCreateTagWithNullNameTest() {
        DomainException exception = Assertions.assertThrows(DomainException.class, () -> createTag(null));
        Assertions.assertEquals(DomainException.Key.TagNameDoesNotBeBlank, exception.getDescription());
    }

    @Test
    void couldNotDeleteTagWithNullIdTest() {
        Tag tag = createTag("name");
        ExecutedCommand<DeleteTagCommand> deleteTagCommand = new ExecutedCommand<>(new DeleteTagCommand(null), Instant.now());
        DomainException exception = Assertions.assertThrows(DomainException.class, () -> tag.delete(deleteTagCommand));
        Assertions.assertEquals(DomainException.Key.WrongTagId, exception.getDescription());
    }

    @Test
    void couldNotDeleteTagWithDifferentIdTest() {
        Tag tag = createTag("name");
        ExecutedCommand<DeleteTagCommand> deleteTagCommand = new ExecutedCommand<>(new DeleteTagCommand(new TagId(UUID.randomUUID())), Instant.now());
        DomainException exception = Assertions.assertThrows(DomainException.class, () -> tag.delete(deleteTagCommand));
        Assertions.assertEquals(DomainException.Key.WrongTagId, exception.getDescription());
    }

    @Test
    void couldNotChangeDeletedTagTest() {
        Tag tag = createTag("name");
        TagId tagId = tag.getId();
        deleteTag(tag);
        ExecutedCommand<ChangeTagCommand> changeTagCommand = new ExecutedCommand<>(new ChangeTagCommand(tagId, "newName", TagColor.RED), NOW);
        DomainException exception = Assertions.assertThrows(DomainException.class, () -> tag.change(changeTagCommand));
        Assertions.assertEquals(DomainException.Key.TagIsDeleted, exception.getDescription());
    }

    @Test
    void couldNotDeleteDeletedTagTest() {
        Tag tag = createTag("name");
        deleteTag(tag);
        DomainException exception = Assertions.assertThrows(DomainException.class, () -> deleteTag(tag));
        Assertions.assertEquals(DomainException.Key.CouldNotDeleteTag, exception.getDescription());
    }


    private void assertTagCreatedEvents(TagCreatedEvent actualEvent, String name, TagColor color) {
        Assertions.assertNotNull(actualEvent.getId());
        Assertions.assertNotNull(actualEvent.getOccurredAt());
        Assertions.assertEquals(NOW, actualEvent.getCreatedAt());
        Assertions.assertEquals(name, actualEvent.getName());
        Assertions.assertEquals(color, actualEvent.getColor());
    }

    private Tag createTag(String name) {
        ExecutedCommand<CreateTagCommand> createTagCommand = new ExecutedCommand<>(new CreateTagCommand(TAG_ID, name, TagColor.BLUE), NOW);
        Tag tag = Tag.rehydrate(List.of());
        tag.create(createTagCommand);
        return tag;
    }

    private void changeTag(Tag tag) {
        ExecutedCommand<ChangeTagCommand> changeTagCommand = new ExecutedCommand<>(new ChangeTagCommand(TAG_ID, "newName", TagColor.RED), Instant.now());
        tag.change(changeTagCommand);
    }

    private void deleteTag(Tag tag) {
        ExecutedCommand<DeleteTagCommand> deleteTagCommand = new ExecutedCommand<>(new DeleteTagCommand(TAG_ID), Instant.now());
        tag.delete(deleteTagCommand);
    }

}