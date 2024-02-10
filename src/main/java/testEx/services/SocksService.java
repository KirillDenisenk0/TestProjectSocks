package testEx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testEx.models.Sock;
import testEx.repositories.SocksRepository;

import java.util.List;

@Service
public class SocksService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public  int findAmountByCottonPartAndColor(String color, String operation, long cottonPart){
        if(operation.equalsIgnoreCase("moreThan")){
         return    findAll().stream().filter(i -> i.getColor().equals(color)).
                    filter(i -> i.getCottonPart() > cottonPart).mapToInt(i -> i.getQuantity()).sum();
        }else if(operation.equalsIgnoreCase("lessThan")){
          return   findAll().stream().filter(i -> i.getColor().equals(color)).
                    filter(i -> i.getCottonPart() < cottonPart).mapToInt(i -> i.getQuantity()).sum();
        }else if(operation.equalsIgnoreCase("equals")){
           return
                   findAll().stream().filter(i -> i.getColor().equals(color)).
                    filter(i -> i.getCottonPart() == cottonPart).mapToInt(i -> i.getQuantity()).sum();
        }
        return 0;
    }

    public List<Sock> findAll(){
        return socksRepository.findAll();
    }
    @Transactional
    public void saveSock(Sock sock){
        socksRepository.save(sock);
    }
}
