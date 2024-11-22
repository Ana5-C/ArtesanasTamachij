package artesanas.artesanas.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import artesanas.artesanas.model.Address;
import artesanas.artesanas.repository.AddressRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressService {   
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAll(){
        return addressRepository.findAll();
    }
    
    //Paginacion
    public List<Address> getAll(int page, int pageSize){
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Address> address = addressRepository.findAll(pageReq);
        return address.getContent();
    }

    public void save(Address address){
        addressRepository.save(address);
    }
    
    public Address getIdAddress(Long idAddress){
        return addressRepository.findById(idAddress).get();
    }

    public void delete (Long idAddress){
        addressRepository.deleteById(idAddress);
    }

	public List<Address> getAddressByCity(String city) {
        return addressRepository.getAddressByCityJPQL(city);
        }
}