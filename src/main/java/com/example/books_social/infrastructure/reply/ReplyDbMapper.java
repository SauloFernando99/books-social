package com.example.books_social.infrastructure.reply;

import com.example.books_social.application.reply.repository.ReplyDto;

public class ReplyDbMapper {
    public static ReplyDocument toDocument(ReplyDto dto) {
        return new ReplyDocument(
            dto.replyId(),
            dto.commentaryId(),
            dto.userId(),
            dto.replyText(),
            dto.createdAt()
        );
    }

    public static ReplyDto toDto(ReplyDocument document) {
        return new ReplyDto(
            document.getId(),
            document.getCommentaryId(),
            document.getUserId(),
            document.getReplyText(),
            document.getCreatedAt()
        );
    }
}
