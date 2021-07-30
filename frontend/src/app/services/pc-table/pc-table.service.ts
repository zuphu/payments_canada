import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class PcTableService {
  dataUrl = 'http://localhost:8080/eventdata';
  detailsUrl = 'http://localhost:8080/details';
  constructor(private http: HttpClient) {}

  getAllData(date?: string, range?: string, pageNo?: string) {
    return this.http.get<any>(this.dataUrl, {
      params: { startDate: `${date}`, range: `${range}`, pageNo: `${pageNo}` },
    });
  }

  getEventDetails(id: number) {
    return this.http.get<any>(this.detailsUrl, {
      params: { id: `${id}` },
    });
  }
}
