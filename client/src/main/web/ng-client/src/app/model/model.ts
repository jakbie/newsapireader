export interface News {
  country?: string;
  category?: string;
  articles: Article[];
}

export interface Article {
  author: string;
  title: string;
  description: string;
  date: string;
  sourceName: string;
  articleUrl: string;
  imageUrl: string;
}
