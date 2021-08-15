import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

export interface Purchase {
  id?: number;
  date?: string;
  mileage?: number;
  litre?: number;
  pricePerLitre?: number;
  price?: number;
  location?: string;
  description?: string;
  credit?: boolean;
}

export interface Data {
 name: string; series: { name: string; value: number; }[];
}


@Injectable({
  providedIn: 'root'
})
export class FuelService {
  API_URL = 'http://localhost:8080';
  ALL_PURCHASE = this.API_URL + '/purchases'

  constructor(private http: HttpClient) { }

  getPurchases(callback: (json: Purchase[]) => void) {
    this.http.get<Purchase[]>(this.ALL_PURCHASE).subscribe(json => callback(json))
  }

  postPurchase(addedValue: Purchase, callback: (json: any) => void) {

     this.http.post(this.ALL_PURCHASE, addedValue,
            {observe: 'response'}).subscribe(response => {
            if (response.status === 201) {
              callback(response.body);
            }
          });
  }


  deletePurchase(id: number, callback: (json:any) => void){
    this.http.delete(this.ALL_PURCHASE + '/' + id,
                {observe: 'response'}).subscribe(response => {
                if (response.status === 200) {
                  callback(response);
                }
              });
  }

   getPriceChartData(years: number, callback: (json:Data[]) => void) {
      this.http.get<Data[]>(this.API_URL + '/chart/price/' + years).subscribe(js => callback(js))
    }
}

