package com.boardgame.boardgameservice.services;

import java.sql.Date;
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

import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void initializeItems() {
        String boardgameDescription = "Naruto boardgame";

        Boardgame boardgame0 = Boardgame.builder()
                .title("Naruto shippuden ultimate ninja storm revolution")
                .description(boardgameDescription)
                .imageUrl("https://cdn-ext.fanatical.com/production/product/1280x720/a774d35b-743d-4277-a719-55a0a01f5b1f.jpg")
                .playerNumberMin(2)
                .playerNumberMax(4)
                .durationMin(15)
                .durationMax(60)
                .ageLimit(10)
                .publisher("Japanime Games, Yoka Boardgames, Yoka by Tsume")
                .price(1500)
                .releaseDate(Date.valueOf("2023-06-12"))
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .build();

        Boardgame boardgame1 = Boardgame.builder()
                .title("Naruto shippuden ultimate ninja storm")
                .description(boardgameDescription)
                .imageUrl("https://cdn-ext.fanatical.com/production/product/1280x720/f74ed3a0-5ba4-4270-bc71-783ea7ec1dfa.jpg")
                .playerNumberMin(2)
                .playerNumberMax(4)
                .durationMin(15)
                .durationMax(60)
                .ageLimit(10)
                .publisher("Japanime Games, Yoka Boardgames, Yoka by Tsume")
                .price(1500)
                .releaseDate(Date.valueOf("2023-06-12"))
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .build();

        Boardgame boardgame2 = Boardgame.builder()
                .title("Naruto shippuden ultimate ninja storm 2")
                .description(boardgameDescription)
                .imageUrl("https://cdn.akamai.steamstatic.com/steam/apps/543870/capsule_616x353.jpg?t=1654696203")
                .playerNumberMin(2)
                .playerNumberMax(4)
                .durationMin(15)
                .durationMax(60)
                .ageLimit(10)
                .publisher("Japanime Games, Yoka Boardgames, Yoka by Tsume")
                .price(1500)
                .releaseDate(Date.valueOf("2023-06-12"))
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .build();

        Boardgame boardgame3 = Boardgame.builder()
                .title("Naruto shippuden ultimate ninja storm 3")
                .description(boardgameDescription)
                .imageUrl("https://hadoantv.com/wp-content/uploads/2023/01/download-Naruto-3-hadoan-tv.jpg")
                .playerNumberMin(2)
                .playerNumberMax(4)
                .durationMin(15)
                .durationMax(60)
                .ageLimit(10)
                .publisher("Japanime Games, Yoka Boardgames, Yoka by Tsume")
                .price(1500)
                .releaseDate(Date.valueOf("2023-06-12"))
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .build();

        Boardgame boardgame4 = Boardgame.builder()
                .title("Naruto shippuden ultimate ninja storm 4")
                .description(boardgameDescription)
                .imageUrl("https://cdn.akamai.steamstatic.com/steam/apps/349040/capsule_616x353.jpg?t=1683624653")
                .playerNumberMin(2)
                .playerNumberMax(4)
                .durationMin(15)
                .durationMax(60)
                .ageLimit(10)
                .publisher("Japanime Games, Yoka Boardgames, Yoka by Tsume")
                .price(1500)
                .releaseDate(Date.valueOf("2023-06-12"))
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .build();

        Boardgame boardgame5 = Boardgame.builder()
                .title("Naruto shippuden ultimate ninja storm 4")
                .description(boardgameDescription)
                .imageUrl("https://gamerwk.sgp1.cdn.digitaloceanspaces.com/2022/04/Ultimate-Ninja-Storm-5.jpg")
                .playerNumberMin(2)
                .playerNumberMax(4)
                .durationMin(15)
                .durationMax(60)
                .ageLimit(10)
                .publisher("Japanime Games, Yoka Boardgames, Yoka by Tsume")
                .price(1500)
                .releaseDate(Date.valueOf("2023-06-12"))
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .build();

        Boardgame boardgame6 = Boardgame.builder()
                .title("Naruto shippuden ultimate ninja storm connections")
                .description(boardgameDescription)
                .imageUrl("https://i.ytimg.com/vi/UqJgPGT99Wg/maxresdefault.jpg")
                .playerNumberMin(2)
                .playerNumberMax(4)
                .durationMin(15)
                .durationMax(60)
                .ageLimit(10)
                .publisher("Japanime Games, Yoka Boardgames, Yoka by Tsume")
                .price(1500)
                .releaseDate(Date.valueOf("2023-06-12"))
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .build();

        Boardgame boardgame7 = Boardgame.builder()
                .title("Naruto shippuden ultimate ninja storm generation")
                .description(boardgameDescription)
                .imageUrl("https://149359733.v2.pressablecdn.com/wp-content/uploads/2012/01/Naruto-Shippuden-Ultimate-Ninja-Storm-Generations.jpg")
                .playerNumberMin(2)
                .playerNumberMax(4)
                .durationMin(15)
                .durationMax(60)
                .ageLimit(10)
                .publisher("Japanime Games, Yoka Boardgames, Yoka by Tsume")
                .price(1500)
                .releaseDate(Date.valueOf("2023-06-12"))
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(null)
                .build();

        boardgameRepository.save(boardgame0);
        boardgameRepository.save(boardgame1);
        boardgameRepository.save(boardgame2);
        boardgameRepository.save(boardgame3);
        boardgameRepository.save(boardgame4);
        boardgameRepository.save(boardgame5);
        boardgameRepository.save(boardgame6);
        boardgameRepository.save(boardgame7);
    }
}
