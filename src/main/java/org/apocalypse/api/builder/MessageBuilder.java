package org.apocalypse.api.builder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

public class MessageBuilder {

    private TextComponent message;

    public MessageBuilder(TextComponent message) {
        this.message = message;
    }

    public static MessageBuilder create(String msg) {
        return new MessageBuilder(Component.text(msg));
    }

    public static MessageBuilder get(TextComponent component) {
        return new MessageBuilder(component);
    }

    public MessageBuilder hover(String hover) {
        this.message = this.message.hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, Component.text(hover)));
        return this;
    }

    public MessageBuilder copy(String copy) {
        this.message = this.message.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, copy));
        return this;
    }

    public MessageBuilder run(String run) {
        this.message = this.message.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, run));
        return this;
    }

    public MessageBuilder suggest(String suggest) {
        this.message = this.message.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggest));
        return this;
    }

    public MessageBuilder url(String url) {
        this.message = this.message.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, url));
        return this;
    }

    public MessageBuilder append(String msg) {
        this.message = this.message.append(Component.text(msg));
        return this;
    }

    public MessageBuilder append(TextComponent component) {
        this.message = this.message.append(component);
        return this;
    }

    public MessageBuilder clone() {
        return new MessageBuilder(this.message.toBuilder().build());
    }

    public TextComponent build() {
        return this.message;
    }
}