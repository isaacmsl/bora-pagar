import type { PageSubject } from '@/types/PageSubject';
import type { SubjectFilters } from '@/types/SubjectFilters';
import axios, { Axios, type AxiosResponse } from 'axios';
import type { Subject } from '@/types/Subject';
export class SubjectService {
  private axiosInstance: Axios;

  public constructor() {
    this.axiosInstance = axios.create({
      baseURL: import.meta.env.VITE_API_URL + '/subjects',
    });
  }

  public async findAllByGoogleId(userGoogleId : string, page : Number = 1) : Promise<PageSubject> {
    const response = await this.axiosInstance.get<any, AxiosResponse<PageSubject>>('', {
        params: {
          page,
          userGoogleId
        }
      });
    
    return response.data;
  }

  public async findAll(filters : SubjectFilters, page : Number = 1) : Promise<PageSubject> {
    // Temporariamente findAll
    const response = await this.axiosInstance.get<any, AxiosResponse<PageSubject>>('', {
        params: {
          page,
          partialName: filters.name,
          department: filters.department
        }
      });
    
    return response.data;
  }

  public async addInterestedUserByCode(credential: string, code: string): Promise<Subject> {
    const response = await this.axiosInstance.post<any, AxiosResponse<Subject>>('/interested', {}, {
      params: {
        code
      },
      headers: {
        credential
      }
    });

    return response.data;
  }

  public async removeInterestedUserByCode(credential: string, code: string): Promise<Subject> {
    const response = await this.axiosInstance.delete<any, AxiosResponse<Subject>>('/interested', {
      params: {
        code
      },
      headers: {
        credential
      }
    });

    return response.data;
  }
}