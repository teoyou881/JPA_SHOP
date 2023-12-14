package JPA_SHOP.JPA_SHOP.api;

import JPA_SHOP.JPA_SHOP.domain.Member;
import JPA_SHOP.JPA_SHOP.service.MemberService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

  private final MemberService memberService;

  @GetMapping("/api/v1/members")
  public List<Member> membersV1() {
    return memberService.findMembers();
  }

  @GetMapping("/api/v2/members")
  public Result membersV2() {
    List<MemberDTO> collect = memberService.findMembers()
                                           .stream()
                                           .map(m -> new MemberDTO(m.getUsername()))
                                           .collect(Collectors.toList());

    return new Result(collect.size(), collect);
  }

  @Data
  @AllArgsConstructor
  static class Result<T> {

    private int size;
    private T data;

  }

  @Data
  @AllArgsConstructor
  static class MemberDTO {

    private String username;

  }

  @PostMapping("/api/v1/members")
  public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
    System.out.println("hahifehi");
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }

  @PostMapping("/api/v2/members")
  public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberDTO request) {
    Member member = new Member();
    member.setUsername(request.getName());
    Long id = memberService.join(member);
    return new CreateMemberResponse(id);
  }

  @PutMapping("/api/members/{id}")
  public UpdateMemberDTO updateMember(
      @PathVariable("id") Long id,
      @RequestBody @Valid UpdateMemberDTO updateDTO) {
    memberService.update(id, updateDTO.getUsername());
    Member findMember = memberService.findOne(id);
    return new UpdateMemberDTO(findMember.getId(), findMember.getUsername());
  }

  @Data
  static class CreateMemberDTO {

    private String name;
  }

  @Data
  static class CreateMemberResponse {

    private Long id;

    public CreateMemberResponse(Long id) {
      this.id = id;
    }
  }

  @Data
  @AllArgsConstructor
  static class UpdateMemberDTO {

    public UpdateMemberDTO() {
    }

    private Long id;
    private String username;
  }
}
