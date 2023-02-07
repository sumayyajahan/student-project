package mscs.hms.controller;

import mscs.hms.model.Inquiry;
import mscs.hms.service.AddressService;
import mscs.hms.service.InquiryService;
import mscs.hms.dto.selectors.UserSelectorDTO;
import mscs.hms.dto.selectors.AddressSelectorDTO;
import mscs.hms.service.IUserService;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InquiryController extends AbsEntityController<Inquiry> {
    
    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/inquiries")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In inquiries view");
        return getListEntitiesModelView(inquiryService.findAll());
    }    

    @GetMapping("/inquiry_new")
    public ModelAndView newInquiryForm() {
        LOG.info("In inquiries new");
        ModelAndView modelAndView = getEditViewModel(new Inquiry(), "new");
        return modelAndView;
    }    

    @GetMapping("/inquiry_edit/{id}")
    public ModelAndView editInquiryForm(@PathVariable(value="id") final Integer inquiryId) {
        LOG.info("In inquiries edit");
        return getEditViewModel(inquiryService.getById(inquiryId), "edit");        
    }

    @PostMapping("/inquiry/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In inquiries delete");
        inquiryService.deleteById(id);
        return getListEntitiesModelView(inquiryService.findAll());
    }

    @PostMapping("/inquiry/edit")
    public ModelAndView processEdit(Inquiry inquiry) {
        LOG.info("In inquiries edit");
        try{
            inquiryService.save(inquiry);
        }
        catch(Exception ex){
            return getEditViewModel(inquiry, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(inquiryService.findAll());
    }

    @PostMapping("/inquiry/new")
    public ModelAndView processNew(Inquiry inquiry) {
        LOG.info("In inquiries new");
        try{
            inquiryService.save(inquiry);
        }
        catch(Exception ex){
            return getEditViewModel(inquiry, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(inquiryService.findAll());
    } 
    
    @Override
    public Class<?> getClassType(){
        return Inquiry.class;
    }
    @Override
    public String getEditViewPath(){
        return "/inquiry_edit";
    }
    @Override
    public String getListViewPath(){
        return "/inquiry_list";
    }
    @Override
    public String getNewViewPath(){
        return "/inquiry_new";
    }
    @Override
    public String getCrudPath(){
        return "/inquiry";
    }
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("systemUser", userService.findAllUsers().stream().map(UserSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("address", addressService.findAll().stream().map(AddressSelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}
