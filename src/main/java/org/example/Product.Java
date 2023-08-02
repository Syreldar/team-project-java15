package org.example;
    public class Product {
        private String name;
        private double price;
        private int availability;
        private String category;
        public Product (String name, double price, int availability, String category){
            this.name = name;
            this.price = price;
            this.availability = availability;
            this.category = category;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public double getPrice() {
            return price;
        }
        public void setPrice(double price) {
            this.price = price;
        }
        public int getAvailability() {
            return availability
                    ;
        }
        public void setAvailability(int availability
        ) {
            this.availability
                    = availability;
        }
        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + " ' " +
            ", price=" + price +
                    ", availability" +
                    "=" + availability
                    +
                    ", category='" + category + " ' " +
            " } ";

        }

    }



