package PR15.Application.controller;

import PR15.Application.model.Phone;
import PR15.Application.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @PostMapping("/phone")
    public void addPhone(@RequestBody Phone phone) {
        phoneService.addPhone(phone);
    }

    @GetMapping("/phones")
    public List<Phone> getAll() {
        return phoneService.getPhone();
    }

    @GetMapping("/phone/{id}")
    public List<Phone> getPhone(@PathVariable UUID id) {
        return phoneService.getPhone(id);
    }

    @DeleteMapping("/phone/{id}")
    public void deletePhone(@PathVariable UUID id) {
        phoneService.deletePhone(id);
    }
}
