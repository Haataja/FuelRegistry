import { Component, OnInit } from '@angular/core';
import { FuelService, Data } from '../fuel.service';

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

    view: any[] = [1000, 400];

    // options
    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showLegend = true;
    showXAxisLabel = true;
    xAxisLabel = 'Kuukausi';
    showYAxisLabel = true;
    yAxisLabel = '€';



    onSelect(event: any) {
      console.log(event);
    }

    single = [
      {
        "name": "Germany",
        "value": 8940000
      },
      {
        "name": "USA",
        "value": 5000000
      },
      {
        "name": "France",
        "value": 7200000
      }
    ];

    multi: Data[] = [];

    refresh(){
        // console.log(`Refreshing table` + new Date().getTime())
        this.service.getPriceChartData((j) => this.multi = j)
      }


}
