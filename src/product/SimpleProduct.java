public SimpleProduct(String name, int price) {
    super(name);
    if (price <= 0) {
        throw new IllegalArgumentException("Цена должна быть строго больше 0");
    }
    this.price = price;
}