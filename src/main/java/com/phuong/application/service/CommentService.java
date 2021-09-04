package com.phuong.application.service;

import com.phuong.application.entity.Comment;
import com.phuong.application.model.request.CreateCommentPostRequest;
import com.phuong.application.model.request.CreateCommentProductRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Comment createCommentPost(CreateCommentPostRequest createCommentPostRequest,long userId);
    Comment createCommentProduct(CreateCommentProductRequest createCommentProductRequest, long userId);
}
