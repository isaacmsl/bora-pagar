import type { PageSubject } from '@/types/PageSubject';
import type { SubjectFilters } from '@/types/SubjectFilters';
import axios, { Axios, type AxiosResponse } from 'axios'
export class SubjectService {
  private axiosInstance : Axios;

  public constructor() {
    this.axiosInstance = axios.create({
      baseURL: import.meta.env.VITE_API_URL + '/subjects',
    });
  }

  public async findAll(filters : SubjectFilters, page : Number = 1) : Promise<PageSubject> {
    // Temporariamente findAll
    const response = await this.axiosInstance.get<any, AxiosResponse<PageSubject>>('/findAll', {
        params: {
          page,
          name: filters.name,
          department: filters.department
        }
      });
    return response.data;
  }
}