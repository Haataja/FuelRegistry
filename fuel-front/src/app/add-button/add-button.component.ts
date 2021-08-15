import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { AddDialogContentComponent } from '../add-dialog-content/add-dialog-content.component';
import { FuelService, Purchase } from '../fuel.service';

@Component({
  selector: 'app-add-button',
  templateUrl: './add-button.component.html',
  styleUrls: ['./add-button.component.css']
})
export class AddButtonComponent implements OnInit {
  @Output() addClicked = new EventEmitter<string>();
  dialogData: Purchase = {};

  constructor(public dialog: MatDialog, public service: FuelService) { }

  ngOnInit(): void {
  }

  openDialog() {
      const dialogRef = this.dialog.open(AddDialogContentComponent, {
            data: {}
          });


      dialogRef.afterClosed().subscribe(result => {
        if(result.date !== undefined){
           this.dialogData = result;
           this.service.postPurchase(this.dialogData, (json: any) => {
              this.addClicked.emit('update');
              // console.log(`Emitting update` +new Date().getTime() )
              });
        }
      });
    }

}

