package es.abelfgdeveloper.common.pagination.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaginationOut {

  private int page;
  private int size;
  private int totalPages;
  private long totalElements;
  private int numberOfElements;
  private boolean first;
  private boolean last;
  private boolean hasNext;
  private boolean hasPrevious;
}
