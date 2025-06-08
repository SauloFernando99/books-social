package com.example.books_social.application.reply.delete;
import com.example.books_social.application.reply.repository.ReplyRepository;
import com.example.books_social.application.shared.exceptions.EntityNotFoundException;

public class DeleteReplyServiceImpl implements DeleteReplyService{
    ReplyRepository replyRepository;

    public DeleteReplyServiceImpl(
        ReplyRepository replyRepository
    ) {
        this.replyRepository = replyRepository;
    }
    @Override
    public void deleteReply(DeleteReplyPresenter presenter, RequestModel request) {
        boolean exists = replyRepository.existsById(request.replyId());

        if (!exists) {
            String message = "There's no reply of id: " + request.replyId() + ".";
            presenter.prepareFailView(new EntityNotFoundException(message));
            return;
        }

        replyRepository.deleteById(request.replyId());

        presenter.prepareSuccessView(new ResponseModel(request.replyId()));

    }
}
