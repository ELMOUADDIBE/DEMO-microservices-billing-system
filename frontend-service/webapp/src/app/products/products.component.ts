import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: any;
  loading: boolean = true; // Add the loading flag

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8888/INVENTORY-SERVICE/products').subscribe(
      (data) => {
        this.products = data;
        this.loading = false; // Data loaded, stop loading indicator
      },
      (error) => {
        console.error('Error loading products', error);
        this.loading = false; // Stop loading even if there’s an error
      }
    );
  }
}
