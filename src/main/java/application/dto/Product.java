package application.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name = "product")
public class Product {
    private String sku;
    private String description;

    @XmlElement(name = "sku")
	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
    }
    
	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
    }
    
    @XmlElement(name = "description")
    @ApiModelProperty(example = "Aspirina", required = true)
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
    }
    
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}