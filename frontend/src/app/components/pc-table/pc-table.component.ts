import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { PcTableService } from '../../services/pc-table/pc-table.service';
import { MatDialog } from '@angular/material/dialog';
import { DetailsDialogComponent } from '../details-dialog/details-dialog.component';

export interface EventData {
  id: number;
  eventDate: string;
  eventType: string;
  eventSummary: string;
  eventSize: string;
}

interface Range {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-pc-table',
  templateUrl: './pc-table.component.html',
  styleUrls: ['./pc-table.component.css'],
})
export class PcTableComponent implements OnInit, AfterViewInit {
  pageEvent: PageEvent | undefined;
  displayedColumns: string[] = [
    'eventDate',
    'eventType',
    'eventSummary',
    'eventSize',
  ];
  dataSource = new MatTableDataSource<EventData>();
  paginationDetails = {
    totalPages: undefined,
    totalElements: undefined,
  };
  @ViewChild(MatTable) table: MatTable<EventData> | undefined;
  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;
  @ViewChild(MatSort) sort: MatSort | undefined;
  datePickerValue: string = '';
  rangeValue: string = '';

  ranges: Range[] = [
    { value: '', viewValue: '---' },
    { value: 'Week', viewValue: 'Week' },
    { value: 'Month', viewValue: 'Month' },
    { value: 'Quarter', viewValue: 'Quarter' },
  ];

  constructor(
    private pcTableService: PcTableService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.pcTableService
      .getAllData(this.datePickerValue, this.rangeValue, '0')
      .subscribe(
        (data) => {
          console.log(data);
          this.paginationDetails = data;
          this.dataSource = data.content;
        },
        (err) => {
          alert('err');
          console.log(err);
        }
      );
  }

  getServerData(event?: PageEvent, newRequest?: boolean) {
    console.log(this.datePickerValue, this.rangeValue);
    this.pcTableService
      .getAllData(
        this.datePickerValue
          ? new Date(this.datePickerValue).getTime().toString()
          : '',
        this.rangeValue,
        newRequest ? '0' : this.paginator?.pageIndex.toString()
      )
      .subscribe(
        (data) => {
          console.log(data);
          this.paginationDetails = data;
          this.dataSource = data.content;
        },
        (err) => {
          alert(JSON.stringify(err));
        }
      );
    if (newRequest && this.paginator) {
      this.paginator.pageIndex = 0;
    }
    return event;
  }

  getRecord(record: EventData) {
    this.pcTableService.getEventDetails(record.id).subscribe(
      (data) => {
        console.log(data);
        const dialogRef = this.dialog.open(DetailsDialogComponent, {
          width: '500px',
          data: data,
        });
      },
      () => {},
      () => {}
    );
  }

  ngAfterViewInit() {
    console.log(this.table);
    console.log(this.paginator);
    this.dataSource.paginator = this.paginator;
  }
}
