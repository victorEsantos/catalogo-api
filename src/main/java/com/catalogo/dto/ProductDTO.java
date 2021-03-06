package com.catalogo.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.catalogo.entities.Category;
import com.catalogo.entities.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 5, max = 60, message =  "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Campo obrigatório")
	@ApiModelProperty(value = "Nome do produto", required = false)
	private String name;
	@ApiModelProperty(value = "Descrição do produto", required = false)
	private String description;
	
	@Positive(message = "O preço deve ser um valor positivo")
	@ApiModelProperty(value = "Preço do produto", required = false)
	private Double price;

	@ApiModelProperty(value = "Url da imagem do produto", required = false)
	private String imgUrl;
	
	@PastOrPresent(message = "A data do produto não pode ser futura")
	@ApiModelProperty(value = "Data criação do produto", required = false)
	private Instant date;

	@ApiModelProperty(value = "Lista de categorias que produto possui", required = false)
	private List<CategoryDTO> categories = new ArrayList<>();

	@Builder
	public ProductDTO(Long id, String name, String description, Double price, String imgUrl, Instant date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}
	
	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.imgUrl = entity.getImgUrl();
		this.date = entity.getDate();
	}
	
	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
		categories.forEach(x -> this.categories.add(new CategoryDTO(x)));
	}

}
