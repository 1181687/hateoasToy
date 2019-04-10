package pt.ipp.isep.dei.project.model.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.HouseRepository;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;


}
