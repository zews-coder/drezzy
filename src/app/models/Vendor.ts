export interface VendorArticles {
    id: number
    title: string,
    description: string,
    creationDate: string,
    price: number,
    discount: number,
    sex: string,
    visible: boolean,
    subtype: string,
    size: string,
    imageUrl: string
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

export interface SingleArticle{
  id: Number,
  title: string,
  description: string,
  price: Number,
  discount: Number,
  size: string,
  imageUrl: string
}
