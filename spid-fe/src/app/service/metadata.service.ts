import {Injectable} from '@angular/core';
import {Metadata} from '../model/metadata';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MetadataService {

  BASE_URL = 'http://localhost:3000/metadata/';

  constructor(private readonly http: HttpClient) {
  }

  public getMetadata(clientId: string): Observable<Metadata> {
    console.log('Get Metadata');
    return this.http.get<Metadata>(`${this.BASE_URL}?client_id=${clientId}`);
  }

}
