package vn.techzen.academy_pnv_12.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
     boolean success;
     String message;
     T data;
     public ApiResponse(boolean success, String message) {
          this.success = success;
          this.message = message;
          this.data = null;
     }
}