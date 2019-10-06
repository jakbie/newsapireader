import {Component, OnDestroy, OnInit} from '@angular/core';
import {ApiService} from "../../service/api-service/api.service";
import {Article} from "../../model/model";
import {CountryService} from "../../service/country-service/country.service";
import {SelectItem} from "primeng/api";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-news-table',
  templateUrl: 'news-table.component.html',
  styleUrls: ['./news-table.component.scss']
})
export class NewsTableComponent implements OnInit, OnDestroy {

  countries: SelectItem[];
  categories: SelectItem[];

  country: string = 'pl';
  category: string = 'technology';

  apiServiceSub: Subscription;
  countryServiceSub: Subscription;

  articles: Article[];

  loading: boolean = false;

  constructor(private apiService: ApiService, private countryService: CountryService) {
  }

  ngOnInit() {
    this.populateCountries();
    this.populateCategories();
    this.apiServiceSub = this.apiService.getArticles(this.country,this.category).subscribe(articles => this.articles = articles);
  }

  populateCountries() {
    this.countryServiceSub = this.countryService.getCountrySelectItems().subscribe(countries => this.countries = countries);
  }

  populateCategories() {
    this.categories = [
        {label: 'Business', value: 'business'},
        {label: 'Entertainment', value: 'entertainment'},
        {label: 'General', value: 'general'},
        {label: 'Health', value: 'health'},
        {label: 'Science', value: 'science'},
        {label: 'Sports', value: 'sports'},
        {label: 'Technology', value: 'technology'},
      ]
  }

  updateArticles() {
    this.apiService.updateArticles(this.country,this.category);
  }

  onDropDownChange() {
    this.updateArticles();
  }

  openLinkInNewTab(url: string) {
    window.open(url, '_blank')
  }

  ngOnDestroy(): void {
    this.apiServiceSub.unsubscribe();
    this.countryServiceSub.unsubscribe();
  }

}
