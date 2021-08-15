import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FuelService, Purchase } from '../fuel.service';
import {NgForm} from '@angular/forms';


@Component({
  selector: 'app-add-dialog-content',
  templateUrl: './add-dialog-content.component.html',
  styleUrls: ['./add-dialog-content.component.css']
})
export class AddDialogContentComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<AddDialogContentComponent>,@Inject(MAT_DIALOG_DATA) public data: Purchase) {}

  ngOnInit(): void {
  }


}
