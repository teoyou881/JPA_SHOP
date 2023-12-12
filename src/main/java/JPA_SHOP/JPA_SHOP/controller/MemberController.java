package JPA_SHOP.JPA_SHOP.controller;

import JPA_SHOP.JPA_SHOP.domain.Address;
import JPA_SHOP.JPA_SHOP.domain.Member;
import JPA_SHOP.JPA_SHOP.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/members/new")
  public String createForm(Model model) {
    model.addAttribute("memberForm", new MemberForm());
    return "members/createMemberForm";
  }

  @PostMapping("/members/new")
  // Using @Validated tells the MemberForm to check null for fields with @NonNull.
  public String create(@Validated MemberForm form, BindingResult result) {

    if (result.hasErrors()) {
      return "members/createMemberForm";
    }

    Address address = new Address(form.getStreet(), form.getCity(), form.getProvince(),
        form.getCountry(), form.getPostalCode());
    Member member = new Member();
    member.setUsername(form.getUsername());
    member.setAddress(address);
    memberService.join(member);
    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
    model.addAttribute("members", memberService.findMembers());
    return "members/memberList";
  }
}
