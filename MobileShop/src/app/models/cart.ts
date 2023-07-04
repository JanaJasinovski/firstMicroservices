import { CartItem } from './cart-item';
import { Product } from './product';

export class Cart{

    id: string;
    userId: number;
    totalQuantity: number;
    totalPrice: number;
    cartItems: CartItem;
}
