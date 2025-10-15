public DiscountedProduct(String name, int basePrice, int discountPercent) {
    super(name);
    if (basePrice <= 0) {
        throw new IllegalArgumentException("Базовая цена должна быть строго больше 0");
    }
    if (discountPercent < 0 || discountPercent > 100) {
        throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0 до 100 включительно");
    }
    this.basePrice = basePrice;
    this.discountPercent = discountPercent;
}