import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import BarraSuperior from './components/barraSuperior'
import Inicio from './components/inicio'
import Simulation from './components/simulation'
import RegisterClient from './components/registerClient'
import Login from './components/login'
import ApplyCredit from './components/applyCredit'
import CreditRequests from './components/creditRequests'
import Pruebas from './components/pruebas'
import CreditEvaluation from './components/creditEvaluation'
import RequirementsEvaluation from './components/requirementsEvaluation'
import RequestStatus from './components/requestStatus'

function App() {
  return (
    <Router>
      <div className="contaninner">
      <BarraSuperior> </BarraSuperior>
          <Routes>
            <Route path= "/" element= {<Inicio/>} />
            <Route path= "/simulacion" element= {<Simulation/>} />            
            <Route path= "/registerCLient" element= {<RegisterClient/>} />
            <Route path= "/login" element= {<Login/>} />
            <Route path= "/solicitar-credito" element= {<ApplyCredit/>} />
            <Route path= "/solicitudes-credito" element= {<CreditRequests/>} />
            <Route path= "/pruebas" element= {<Pruebas/>} />            
            <Route path= "/evaluacion-credito/:id" element= {<CreditEvaluation/>} />
            <Route path= "/evaluacion-requerimientos/:id" element= {<RequirementsEvaluation/>} />
            <Route path= "/estado-solicitud/:id" element= {<RequestStatus/>} />
          </Routes>


      </div>
    </Router>
  )
}

export default App
