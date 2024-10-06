import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{
  customers: any;    // Holds the customer data
  loading: boolean = true;    // Flag for loading indicator

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    // Fetch customers from the API
    this.http.get('http://localhost:8888/CUSTOMER-SERVICE/customers').subscribe(
      (data) => {
        this.customers = data;
        this.loading = false;
      },
      (error) => {
        console.error('Error loading customers', error);
        this.loading = false;
      }
    );
  }
}
