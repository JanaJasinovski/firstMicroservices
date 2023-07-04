import { Address } from "./address";
import { CartItem } from "./cart-item";

export class Order {
    constructor(
      public id?: string,
      public totalAmount?: number,
      public totalPrice?: number,
      public shippingAddress?: Address,
      public orderStatus?: string,
      public dateCreated?: Date,
      public deliveredDate?: Date,
      public userId?: number,
      public cartItems?: CartItem[]
) {
}
}
  