import { CartItem } from "./cart-item";

export class Product {
    constructor(
      public id?: number,
      public name?: string,
      public price?: number,
      public amount?: number,
      public picture?: string,
      public categoryName?: string,
      public userId?: number
) {
}
}
  