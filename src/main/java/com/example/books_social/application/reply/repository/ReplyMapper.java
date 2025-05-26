package com.example.books_social.application.reply.repository;

import com.example.books_social.domain.model.comentary.*;
import com.example.books_social.domain.model.reply.Reply;
import com.example.books_social.domain.model.reply.ReplyId;
import com.example.books_social.domain.model.reply.ReplyText;

public class ReplyMapper {
    public static Reply fromDto(ReplyDto dto) {

        return new Reply(
                new ReplyId(dto.replyId()),
                new CommentaryId(dto.commentaryId()),
                dto.userId(),
                new ReplyText(dto.replyText()),
                dto.createdAt()
        );
    }

    public static ReplyDto toDto(Reply reply) {
        return new ReplyDto(
            reply.getReplyId().getValue(),
            reply.getCommentaryId().getValue(),
            reply.getUserId(),
            reply.getReplyText().value(),
            reply.getCreatedAt()
        );
    }
}
