package org.uma.api.model.request;

import java.io.File;

import org.uma.api.model.request.SignupRequest.Builder;

public class ProductRequest {

	private String productName;
	private String productAddedBy;
	private String productCategory;
	private String productSubCategory;
	private int productPrice;
	private String productDescription;
	private String productFor;
	private File productImage; // Changed to File type


	public ProductRequest(String productName, String productAddedBy, String productCategory, String productSubCategory,
			int productPrice, String productDescription, String productFor, File productImage) {
		this.productName = productName;
		this.productAddedBy = productAddedBy;
		this.productCategory = productCategory;
		this.productSubCategory = productSubCategory;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productFor = productFor;
		this.productImage = productImage;
	}

	// Getters and setters

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAddedBy() {
		return productAddedBy;
	}

	public void setProductAddedBy(String productAddedBy) {
		this.productAddedBy = productAddedBy;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductSubCategory() {
		return productSubCategory;
	}

	public void setProductSubCategory(String productSubCategory) {
		this.productSubCategory = productSubCategory;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductFor() {
		return productFor;
	}

	public void setProductFor(String productFor) {
		this.productFor = productFor;
	}

	public File getProductImage() {
		return productImage;
	}

	public void setProductImage(File productImage) {
		this.productImage = productImage;
	}

	@Override
	public String toString() {
		return "Product{" + "productName='" + productName + '\'' + ", productAddedBy='" + productAddedBy + '\''
				+ ", productCategory='" + productCategory + '\'' + ", productSubCategory='" + productSubCategory + '\''
				+ ", productPrice=" + productPrice + ", productDescription='" + productDescription + '\''
				+ ", productFor='" + productFor + '\'' + ", productImage=" + productImage + '}';
	}

	public static class Builder {

		private String productName;
		private String productAddedBy;
		private String productCategory;
		private String productSubCategory;
		private int productPrice;
		private String productDescription;
		private String productFor;
		private File productImage;

		public Builder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public Builder productAddedBy(String productAddedBy) {
			this.productAddedBy = productAddedBy;
			return this;// current object
		}

		public Builder productCategory(String productCategory) {
			this.productCategory = productCategory;
			return this;
		}

		public Builder productSubCategory(String productSubCategory) {
			this.productSubCategory = productSubCategory;
			return this;
		}

		public Builder productPrice(int productPrice) {
			this.productPrice = productPrice;
			return this;
		}

		public Builder productDescription(String productDescription) {
			this.productDescription = productDescription;
			return this;
		}

		public Builder productFor(String productFor) {
			this.productFor = productFor;
			return this;
		}

		public Builder productImage(File productImage) {
			this.productImage = productImage;
			return this;
		}

		public ProductRequest build() {
			ProductRequest productRequest = new ProductRequest(productName, productAddedBy, productCategory,
					productSubCategory, productPrice, productDescription, productFor, productImage);
			return productRequest;
		}
	}

}
