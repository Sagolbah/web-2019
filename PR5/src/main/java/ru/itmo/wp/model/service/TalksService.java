package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.repository.TalksRepository;
import ru.itmo.wp.model.repository.impl.TalksRepositoryImpl;

import java.util.List;

public class TalksService {
    private final TalksRepository talksRepository = new TalksRepositoryImpl();

    public List<Talk> findAllWithUser(long id) {
        return talksRepository.findAllById(id);
    }

    public void save(Talk talk){
        talksRepository.save(talk);
    }
}
