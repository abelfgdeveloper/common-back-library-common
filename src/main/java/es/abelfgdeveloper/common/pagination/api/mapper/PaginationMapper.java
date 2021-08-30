package es.abelfgdeveloper.common.pagination.api.mapper;

import es.abelfgdeveloper.common.dto.pagination.PaginationResponseResource;
import es.abelfgdeveloper.common.pagination.domain.PaginationIn;
import es.abelfgdeveloper.common.pagination.domain.PaginationOut;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginationMapper {

  public static PaginationIn map(Integer page, Integer size) {
    return PaginationIn.builder().page(validatePage(page)).size(validateSize(size)).build();
  }

  public static PaginationResponseResource map(PaginationOut pagination) {
    return PaginationResponseResource.builder()
        .page(pagination.getPage() + 1)
        .size(pagination.getSize())
        .totalPages(pagination.getTotalPages())
        .totalElements(pagination.getTotalElements())
        .numberOfElements(pagination.getNumberOfElements())
        .first(pagination.isFirst())
        .last(pagination.isLast())
        .hasNext(pagination.isHasNext())
        .hasPrevious(pagination.isHasPrevious())
        .build();
  }

  private static int validatePage(Integer page) {
    if (page == null) {
      return 0;
    } else if (page < 1) {
      // TODO Create own exception
      throw new RuntimeException();
    } else {
      return page - 1;
    }
  }

  private static int validateSize(Integer size) {
    if (size == null) {
      return 10;
    } else if (size < 1) {
      // TODO Create own exception
      throw new RuntimeException();
    } else if (size > 100) {
      // TODO Create own exception
      throw new RuntimeException();
    } else {
      return size;
    }
  }
}
