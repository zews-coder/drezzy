export interface VendorArticles {
    id: Number
    title: String,
    description: String,
    creationDate: string,
    price: Number,
    discount: Number,
    sex: String,
    visible: boolean,
    subtype: String,
    size: String
  }

export interface Bill{
    id: Number,
    customerId: Number,
    articleList: VendorArticles[];
    date: string,
    status: String,
    price: Number,
    showArticles?: boolean;
}