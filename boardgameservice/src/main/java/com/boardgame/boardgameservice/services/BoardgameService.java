package com.boardgame.boardgameservice.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boardgame.boardgameservice.models.Boardgame;
import com.boardgame.boardgameservice.repositories.BoardgameRepository;
import com.boardgame.boardgameservice.requests.CreateBoardgameRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardgameService {
    @Autowired
    private final BoardgameRepository boardgameRepository;

    public Object create(CreateBoardgameRequest request) {

        Boardgame boardgame = Boardgame.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .playerNumberMin(request.getPlayerNumberMin())
                .playerNumberMax(request.getPlayerNumberMax())
                .durationMin(request.getDurationMin())
                .durationMax(request.getDurationMax())
                .ageLimit(request.getAgeLimit())
                .publisher(request.getPublisher())
                .price(request.getPrice())
                .releaseDate(request.getReleaseDate())
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .build();

        boardgameRepository.save(boardgame);


        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("boardgame", boardgame);
        response.put("message", "Created successfully.");
        response.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));

        return response;
    }

    public Object find(UUID id) {
        Boardgame boardgame = boardgameRepository.findById(id).orElseThrow();

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("boardgame", boardgame);
        response.put("message", "Found successfully.");
        response.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));

        return response;
    }

    public Object getAll() {
        List<Boardgame> boardgames = boardgameRepository.findAll();

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("boardgames", boardgames);
        response.put("message", "Get all boardgame successfully.");
        response.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));

        return response;
    }

    public Object delete(UUID id) {
        boardgameRepository.deleteById(id);

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Deleted successfully.");
        response.put("timestamp", Timestamp.valueOf(LocalDateTime.now()));

        return response;
    }
}
