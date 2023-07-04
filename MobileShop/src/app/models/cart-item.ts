import { Product } from './product';

export class CartItem {

    id: string;
    userId: number;
    productId: number;
    productName: string;
    amount: number;
    productPrice: number;
    picture: string;

    constructor(product: Product) {
        this.productId = product.id;
        this.userId = product.userId;
        this.picture = product.picture;
        this.productPrice = product.price;
        this.amount = product.amount;
        this.productName = product.name;
    }
}
