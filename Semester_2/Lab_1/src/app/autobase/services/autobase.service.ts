import { inject, Injectable } from '@angular/core';
import { Autobase } from '../model/Autobase';
import { Observable } from 'rxjs';
import { collection, collectionData, deleteDoc, doc, Firestore, getDoc, setDoc } from '@angular/fire/firestore';

@Injectable({
  providedIn: 'root'
})
export class AutobaseService {
  private db = inject(Firestore);
  private dbPath = '/list-autobase';
  private autobase: Observable<Autobase[]>;

  constructor() {
    this.autobase = collectionData(collection(this.db, this.dbPath)) as Observable<Autobase[]>;
  }

  async getAutobase(): Promise<Autobase[]> {
    return new Promise((resolve, reject) => {
      this.autobase.subscribe(
        data => resolve(data),
        error => reject(error)
      );
    });
  }

  async add(autobase: Autobase): Promise<void> {
    const autobaseCollection = collection(this.db, this.dbPath);
    const docRef = doc(autobaseCollection, autobase.id.toString());
    await setDoc(docRef, autobase);
  }

  async getById(id: number): Promise<Autobase | null> {
    const autobaseDoc = doc(this.db, `${this.dbPath}/${id}`);
    const docSnapshot = await getDoc(autobaseDoc);
    return docSnapshot.exists() ? (docSnapshot.data() as Autobase) : null;
  }

  async getMaxId(): Promise<number> {
    const autobase = await this.getAutobase();
    return autobase.length;
  }

  async deleteById(id: number): Promise<void> {
    const autobaseDoc = doc(this.db, `${this.dbPath}/${id}`);
    await deleteDoc(autobaseDoc);
  }

  async update(updatedAutobase: Autobase): Promise<void> {
    const autobaseDoc = doc(this.db, `${this.dbPath}/${updatedAutobase.id}`);
    await setDoc(autobaseDoc, updatedAutobase, { merge: true });
  }
}
