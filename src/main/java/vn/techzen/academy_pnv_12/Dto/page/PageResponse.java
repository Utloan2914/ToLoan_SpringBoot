package vn.techzen.academy_pnv_12.Dto.page;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    List<T> content;
    PageCustom<T> page;

    public PageResponse(Page<T> page) {
        content = page.getContent();
        this.page = new PageCustom<>(page);
    }
}
