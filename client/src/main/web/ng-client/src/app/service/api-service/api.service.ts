import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable, ReplaySubject} from "rxjs";
import {Article, News} from "../../model/model";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  
  readonly api_endpoint = '/news';

  articlesSource: ReplaySubject<Article[]> = new ReplaySubject<Article[]>(1);

  constructor(private httpClient: HttpClient) { }

  getArticles(country:string, category:string):Observable<Article[]> {

    this.updateArticles(country, category);
    return this.articlesSource.asObservable();

  }

  updateArticles(country:string, category:string) {

    let apiUri = `${this.api_endpoint}/${country}/${category}`;

    this.httpClient.get<News>(apiUri)
      .pipe(map(news => news.articles))
      .subscribe(articles => this.articlesSource.next(articles));

  }

}
