export interface Address {
    street: string;
    city: string;
    state: string;
    zip: string;
    phone: string;
  }
  
export interface CardInfo {
    cardHolder: string;
    cardNumber: string;
    expiryDate: string;
    cvv: string;
  }
  
export interface SignUpDTO {
    username: string;
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    address: Address;
    cardInfo: CardInfo;
  }

  export interface BillRequest {
    articleIds: string[];
    address: Address;
    cardInfo: CardInfo;
  }