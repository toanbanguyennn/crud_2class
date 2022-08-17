package com.example.crud_province.controller;

import com.example.crud_province.model.Country;
import com.example.crud_province.model.Province;
import com.example.crud_province.service.ICountryService;
import com.example.crud_province.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/province")
public class ProvinceController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ICountryService countryService;

    @ModelAttribute("provinces")
    public List<Province> provinceList() {
        return provinceService.findAll();
    }

    @ModelAttribute("countries")
    public List<Country> countryList() {
        return countryService.findAll();
    }

    @GetMapping
    public ModelAndView findAllProvince() {
        return new ModelAndView("display");
    }

    @GetMapping("/create")
    public ModelAndView createProvince() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("province") Optional<Province> province,
                         RedirectAttributes redirectAttributes) {
setImageOfProvince(province.get());
        if (province.isPresent()) {
            MultipartFile multipartFile = province.get().getImage();
            String imageUrl = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(),new File(fileUpload + multipartFile.getOriginalFilename()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            province.get().setImageUrl(imageUrl);
            provinceService.save(province.get());
        } else {
            redirectAttributes.addFlashAttribute("message", "Update fail!");
        }
        redirectAttributes.addFlashAttribute("message", "Update successfully!");
        return "redirect:/province";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateProvince(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("update");
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            modelAndView.addObject("province", province.get());
        } else {
            modelAndView.addObject("message", "Not exist province!");
        }
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("province") Optional<Province> province,
                         RedirectAttributes redirectAttributes) {

        if (province.isPresent()) {
            setImageOfProvince(province.get());
            provinceService.save(province.get());
        } else {
            redirectAttributes.addFlashAttribute("message", "Update fail!");
        }
        redirectAttributes.addFlashAttribute("message", "Update ok!");
        return "redirect:/province";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes) {
        provinceService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Delete ok roi!");
        return "redirect:/province";
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search") Optional<String> name) {
        ModelAndView modelAndView = new ModelAndView("display");
        if (name.isPresent()) {
            List<Province> provinces = provinceService.findBySearch(name.get());
            modelAndView.addObject("provinces", provinces);
        }
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Optional<Province> province = provinceService.findById(id);
        modelAndView.addObject("province", province);
        return modelAndView;
    }

    private void setImageOfProvince(Province province) {
        MultipartFile image = province.getImage();
        String imageUrl = image.getOriginalFilename();
        try {
            FileCopyUtils.copy(image.getBytes(), new File(fileUpload + image.getOriginalFilename()));
        } catch (IOException ex) {
            System.err.println("Error");
        }
        province.setImageUrl(imageUrl);
    }
    @ModelAttribute("provinces")
    public Page<Province> province() {
        return provinceService.findAll(Pageable.unpaged());
    }
    @GetMapping
    public ModelAndView findAllProvince(@PageableDefault(value = 4) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("display");
        Page<Province> provinces = provinceService.findAll(pageable);
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

}
