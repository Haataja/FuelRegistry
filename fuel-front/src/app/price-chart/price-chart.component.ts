import { Component, OnInit } from '@angular/core';
import { FuelService, Data } from '../fuel.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-price-chart',
  templateUrl: './price-chart.component.html',
  styleUrls: ['./price-chart.component.css']
})
export class PriceChartComponent implements OnInit {

  constructor(private service: FuelService) { }

  ngOnInit(): void {
    this.refresh();
  }

   public data: any = {numberOfYears: 3};
   public multi: Data[] = [];

    // options
    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showLegend = true;
    showXAxisLabel = true;
    xAxisLabel = 'Kuukausi';
    showYAxisLabel = true;
    yAxisLabel = '€';
    legendTitle: string = 'Vuodet';

    onSelect(event: any) {
      console.log(event);
    }

    refresh(){
        // console.log(`Refreshing table` + new Date().getTime())
        this.service.getPriceChartData(this.data.numberOfYears, (j) => this.multi = j)
      }


}
