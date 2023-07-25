package org.edupoll.service;

import java.util.List;

import org.edupoll.model.dto.request.AddReplyRequestData;
import org.edupoll.model.entity.Reply;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
	@Autowired
	ReplyRepository replyRepository;
	@Autowired
	MoimRepository moimRepository;

	public void createNewReplay(AddReplyRequestData data) {
		Reply reply = new Reply();
		reply.setMoim(moimRepository.findById(data.getMoimId()).get());
		reply.setText(data.getText());

		replyRepository.save(reply);

	}

	public List<Reply> getReplysByMoimId(String moimId, int p) {
		return replyRepository.findByMoimId(moimId, PageRequest.of(p - 1, 5, Sort.by(Direction.ASC, "id")));
	}
}
