package br.com.springawsms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"id", "title", "author", "launchDate", "price"})
public class BookVO extends RepresentationModel<BookVO> {
    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String title;

    private String author;

    private Date launchDate;

    private BigDecimal price;
}
