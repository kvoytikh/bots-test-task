import React, { useState } from 'react';
import axios from "axios";

const App = () => {
  const [users, setUsers] = useState([]);
  const [degree, setDegree] = useState([]);
  const [loading, setLoading] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');

  const fetchUsers = async () => {
    try {
      setLoading(true);


      const response = await fetch(`http://localhost:8080/api/university/search?query=${searchTerm}`);
      const result = await response.json();
      console.log(result);

      setUsers(result);
    } catch (error) {
      console.error('Помилка при отриманні даних:', error);
    } finally {
      setLoading(false);
    }
  };

  const fetchDegree = async (id) => {
    try {
      setLoading(true);
      setDegree([]);


      const response = await fetch(`http://localhost:8080/api/university/department/${id}/statistics`);
      const result = await response.json();
      console.log(result);

      setDegree((prevData) => [...prevData, result]);
    } catch (error) {
      console.error('Помилка при отриманні даних:', error);
    } finally {
      setLoading(false);
    }
  };

  const updateData = async (id) => {
    try {
      setLoading(true);


      await axios.put(`http://localhost:8080/api/university/promote/${id}`,{id:1});
    } catch (error) {
      console.error('Помилка при оновленні даних:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
      <div>
          <p>Oxford <button onClick={() => fetchDegree(1)} disabled={loading}>
              Зробити запит
          </button></p>
          <p>Garvard <button onClick={() => fetchDegree(2)} disabled={loading}>
              Зробити запит
          </button></p>
          <p>Greenwich <button onClick={() => fetchDegree(3)} disabled={loading}>
              Зробити запит
          </button></p>
          <input
              type="text"
              placeholder="Пошук за іменем"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
          />
          <button onClick={() => fetchUsers(searchTerm)} disabled={loading}>
              Зробити запит
          </button>

          {/*<button onClick={updateData} disabled={loading}>*/}
          {/*  Викликати PUT-запит*/}
          {/*</button>*/}

          {loading && <p>Завантаження...</p>}

          {users.length > 0 && (
              <table>
                  <thead>
                  <tr>
                      <th>ID</th>
                      <th>Name</th>

                  </tr>
                  </thead>
                  <tbody>
                  {users.map((item) => (
                      <tr key={item.id}>
                          <td>{item.id}</td>
                          <td>{item.name}</td>
                          <td>
                              <button onClick={() => updateData(2)}>
                                  Підвищити
                              </button>
                          </td>

                      </tr>
                  ))}
                  </tbody>
              </table>
          )}

          {degree.length > 0 && (
              <table>
                  <thead>
                  <tr>
                      <th>Доценти</th>
                      <th>Професори</th>
                      <th>Асистенти</th>
                  </tr>
                  </thead>
                  <tbody>
                  {degree.map((item) => (
                      <tr key={item.id}>
                          <td>{item.ASSOCIATE_PROFESSOR}</td>
                          <td>{item.PROFESSOR}</td>
                              <td>{item.ASSISTANT}</td>
                      </tr>
                  ))}
                  </tbody>
              </table>
          )}

      </div>
  );
};

export default App;