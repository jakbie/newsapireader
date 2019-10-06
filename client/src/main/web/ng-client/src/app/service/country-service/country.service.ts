import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {SelectItem} from "primeng/api";
import {Observable} from "rxjs";

interface Country {
  Code: string;
  Name: string;
}

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  constructor(private httpClient: HttpClient) {
  }

  getCountrySelectItems(): Observable<SelectItem[]> {
    // .json pochodzi ze strony https://datahub.io/core/country-list
    return this.httpClient.get<Country[]>('assets/iso-3166.json').pipe(
              map((countries:Country[]) => countries.map(country => <SelectItem>{ label: country.Name, value: country.Code.toLowerCase()}))
           );
  }
}
